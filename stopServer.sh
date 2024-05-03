#!/bin/bash

SERVER_PID_FILE="./server.pid"
BS_PID_FILE="./browser-sync.pid"

# Function to check if the process is running
is_process_running() {
    [ -f "$1" ] && ps -p $(cat $1) > /dev/null 2>&1
}

# Function to check if the running process is indeed our server or browser-sync
is_correct_process() {
    PID_FILE="$1"
    PROCESS_KEYWORD="$2"
    if [ -f "$PID_FILE" ]; then
        PID=$(cat $PID_FILE)
        if ps -p $PID -o args | grep -q "$PROCESS_KEYWORD"; then
            return 0
        fi
    fi
    return 1
}

# Function to kill all browser-sync related node processes
kill_browser_sync_processes() {
    echo "Stopping all browser-sync processes..."
    pkill -f 'node .*/browser-sync'
    pkill -f 'open -W http://localhost:300'
    echo "All browser-sync processes stopped."
}

# Stop any previously running server instance
if is_process_running $SERVER_PID_FILE && is_correct_process $SERVER_PID_FILE "gradlew"; then
    echo "Stopping server..."
    kill $(cat $SERVER_PID_FILE)
    rm -f $SERVER_PID_FILE
    echo "Server stopped successfully."
else
    echo "No running server process found, or PID is not associated with the expected server command."
    [ -f $SERVER_PID_FILE ] && rm -f $SERVER_PID_FILE
fi

# Stop browser-sync processes
kill_browser_sync_processes

# List of files to check and remove if they exist
files=("server.log" "server.pid" "browser-sync.pid")

# Loop through the list of files
for file in "${files[@]}"; do
  if [[ -f "$file" ]]; then
    echo "Cleanup file: $file..."
    rm "$file"
  fi
done