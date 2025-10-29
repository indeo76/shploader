#!/bin/bash

# Ustawienia
TARGET_DIR="/deployment/java/shploader/deployment"

# Przejście do katalogu
cd "$TARGET_DIR" || { echo "Nie można wejść do katalogu docelowego."; exit 1; }

# Uruchomienie docker-compose
echo "Uruchamiam docker-compose..."
docker-compose -f docker-compose.prod.yaml up
