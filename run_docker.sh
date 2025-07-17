cd ./Reservation-System
docker-compose -f docker-compose.yml down
docker build -t reservation-system:v5.0 .
docker-compose -f docker-compose.yml up -d