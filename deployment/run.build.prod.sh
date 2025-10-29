#!/bin/bash

# Ustawienia
REPO_URL="https://github.com/indeo76/shploader.git"
TARGET_DIR="/deployment/java/shploader/deployment"

# Pobieranie repozytorium
echo "Klonuję repozytorium..."
git clone "$REPO_URL" "$TARGET_DIR"

# Przejście do katalogu
cd "$TARGET_DIR" || { echo "Nie można wejść do katalogu docelowego."; exit 1; }

# Uruchomienie docker-compose
echo "Uruchamiam docker-compose..."
docker-compose -f docker-compose.prod.yaml up --build
