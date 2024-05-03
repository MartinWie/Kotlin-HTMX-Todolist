trap 'echo "Script interrupted by user, performing cleanup..."; bash stopServer.sh; exit' SIGINT SIGTERM EXIT

while sleep 1; do
  find ./src/main -name "*.kt" | entr -d sh -c 'bash startServer.sh; exit_status=$?;
  if [ $exit_status -eq 130 ]; then
    echo "startServer.sh was interrupted by the user."
    exit 130
  fi'
  if [ $? -eq 130 ]; then
    echo "Detected interruption of startServer.sh by the user."
    bash stopServer.sh
    break
  fi
done
