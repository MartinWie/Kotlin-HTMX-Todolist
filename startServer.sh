#!/bin/bash

# Compile Tailwind CSS
npx tailwindcss -i ./src/main/resources/static/input.css -o ./src/main/resources/static/output.css || { echo "Tailwind CSS compilation failed"; exit 1; }

PID_FILE="./server.pid"
BS_PID_FILE="./browser-sync.pid"
SERVER_START_SUCCESS_FILE="./server_start_success.tmp"

# Check if the server process is running
is_process_running() {
    [ -f "$1" ] && ps -p $(cat $1) > /dev/null 2>&1
}

# Verify the running process is the server
is_correct_process() {
    SERVER_KEYWORD="$2"
    if [ -f "$1" ]; then
        PID=$(cat $1)
        if ps -p $PID -o args | grep -q "$SERVER_KEYWORD"; then
            return 0
        fi
    fi
    return 1
}

# Stop any previously running server instance
if is_process_running $PID_FILE && is_correct_process $PID_FILE "gradlew"; then
    echo "Stopping server..."
    kill $(cat $PID_FILE)
    rm -f $PID_FILE
else
    echo "No running server process found, or PID is not associated with the expected server command."
    [ -f $PID_FILE ] && rm -f $PID_FILE
fi

# Start the server and save the new PID
echo "Starting server..."
./gradlew run > ./server.log 2>&1 &
SERVER_PID=$!
echo $SERVER_PID > $PID_FILE
echo "Server starting with PID: $SERVER_PID, waiting for readiness..."
echo "false" > $SERVER_START_SUCCESS_FILE  # Initialize as false

# Wait for server readiness
tail -f ./server.log | while IFS= read -r line; do
    echo "$line"
    if [[ "$line" == *"BUILD FAILED in"* ]]; then
        echo "Build failure detected. Pausing before re-checking server status..."
        sleep 5
        echo "false" > $SERVER_START_SUCCESS_FILE
        pkill -P $$ tail
        break
    elif [[ "$line" == *"INFO  ktor.application - Responding at"* ]]; then
        echo "Server started and is now responding."
        echo "true" > $SERVER_START_SUCCESS_FILE
        pkill -P $$ tail
        break
    fi
done

# Read the success status from the file
server_start_success=$(< $SERVER_START_SUCCESS_FILE)

# If the server started successfully, check and possibly reload browser-sync
if [ "$server_start_success" = "true" ]; then
    if is_process_running $BS_PID_FILE && is_correct_process $BS_PID_FILE "browser-sync"; then
        echo "Reloading browser-sync..."
        browser-sync reload
    else
        echo "Starting browser-sync..."
        browser-sync start --proxy localhost:8080 & echo $! > $BS_PID_FILE
        disown $!
        echo "Browser-sync started with new PID: $(cat $BS_PID_FILE)"
    fi
else
    echo "Skipping browser-sync due to server start failure."
fi

# Cleanup
rm -f $SERVER_START_SUCCESS_FILE
