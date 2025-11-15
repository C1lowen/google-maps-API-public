# 🍽️ Google Maps Restaurant API

REST API for finding nearby restaurants using Google Maps Places API. The application provides a convenient interface for searching restaurants by geolocation and retrieving detailed information about them.

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Requirements](#-requirements)
- [Installation and Setup](#-installation-and-setup)
- [Configuration](#-configuration)
- [API Endpoints](#-api-endpoints)
- [Request Examples](#-request-examples)
- [Project Structure](#-project-structure)
- [Error Handling](#-error-handling)

## ✨ Features

- 🔍 Search for nearby restaurants by coordinates and radius
- 📍 Calculate distance to restaurants using the Haversine formula
- 📸 Retrieve restaurant photos
- 📊 Detailed restaurant information (rating, address, opening hours, website)
- ✅ Input parameter validation
- 🛡️ Global error handling

## 🛠 Technology Stack

- **Java 17**
- **Spring Boot 3.5.3**
- **Google Maps Services Java Client 2.2.0**
- **Lombok** - for reducing boilerplate code
- **Spring Validation** - for input data validation
- **Maven** - build system


## 🚀 Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/google-maps-restaurant-API.git
cd google-maps-restaurant-API
```

#### Using Docker:

**Build the Docker image:**
```bash
docker build -t google-maps-restaurant-api .
```

**Run the container:**
```bash
docker run -p 8080:8080 -e GOOGLE_MAPS_API_KEY=your_api_key_here google-maps-restaurant-api
```

**Using Docker Compose:**
```bash
# Set your API key as environment variable
export GOOGLE_MAPS_API_KEY=your_api_key_here

# Or create a .env file with:
# GOOGLE_MAPS_API_KEY=your_api_key_here

docker-compose up -d
```

To stop the container:
```bash
docker-compose down
```

## ⚙️ Configuration

### Setting the API Key

You can set the Google Maps API key in two ways:

**Option 1: Environment Variable (Recommended for Docker)**
```bash
export GOOGLE_MAPS_API_KEY=your_api_key_here
```

**Option 2: Direct Configuration (For local development)**
Edit `src/main/resources/application.properties`:
```properties
google.api.key=your_api_key_here
```

⚠️ **Note:** Never commit the API key to version control. Use environment variables in production.

## 📡 API Endpoints

### 1. Search Nearby Restaurants

**GET** `/api/places/restaurants`

Search for restaurants within a specified radius from given coordinates.

#### Query Parameters

| Parameter | Type | Required | Description | Constraints |
|-----------|------|----------|-------------|-------------|
| `lat` | Double | Yes | Latitude | -90 to 90 |
| `lng` | Double | Yes | Longitude | -180 to 180 |
| `radius` | Integer | Yes | Search radius in meters | 1 to 10000 |

#### Example Response

```json
[
  {
    "place_id": "ChIJN1t_tDeuEmsRUsoyG83frY4",
    "name": "Restaurant Name",
    "rating": 4.5,
    "description": "123 Main Street, City",
    "distance": "1.2км",
    "photo": "https://maps.googleapis.com/maps/api/place/photo?..."
  }
]
```

---

### 2. Get Restaurant Details

**GET** `/api/places/restaurants/{id}`

Get detailed information about a specific restaurant.

#### Path Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | String | Restaurant Place ID |

#### Query Parameters

| Parameter | Type | Required | Description | Constraints |
|-----------|------|----------|-------------|-------------|
| `lat` | Double | Yes | User latitude | -90 to 90 |
| `lng` | Double | Yes | User longitude | -180 to 180 |

#### Example Response

```json
{
  "name": "Restaurant Name",
  "rating": 4.5,
  "description": "Detailed description of the restaurant",
  "address": "123 Main Street, City, Country",
  "website": "https://restaurant-website.com",
  "distance": "1.2км",
  "opening_hours": {
    "openNow": true,
    "weekdayText": [
      "Monday: 9:00 AM – 10:00 PM",
      "Tuesday: 9:00 AM – 10:00 PM"
    ]
  },
  "photos": [
    "https://maps.googleapis.com/maps/api/place/photo?...",
    "https://maps.googleapis.com/maps/api/place/photo?...",
    "https://maps.googleapis.com/maps/api/place/photo?..."
  ]
}
```

## 💡 Request Examples

### Search for restaurants within 1000 meters radius

```bash
curl "http://localhost:8080/api/places/restaurants?lat=55.7558&lng=37.6173&radius=1000"
```

### Get restaurant details

```bash
curl "http://localhost:8080/api/places/restaurants/ChIJN1t_tDeuEmsRUsoyG83frY4?lat=55.7558&lng=37.6173"
```

### Using Postman

1. **GET request to search restaurants:**
   - URL: `http://localhost:8080/api/places/restaurants`
   - Params:
     - `lat`: `55.7558`
     - `lng`: `37.6173`
     - `radius`: `1000`

2. **GET request for restaurant details:**
   - URL: `http://localhost:8080/api/places/restaurants/{place_id}`
   - Params:
     - `lat`: `55.7558`
     - `lng`: `37.6173`

## ⚠️ Error Handling

The API uses global error handling and returns structured error responses.

### Response Codes

| Code | Description |
|------|-------------|
| `200 OK` | Successful request |
| `400 Bad Request` | Invalid request parameters |
| `502 Bad Gateway` | Error when accessing external service (Google Maps API) |

### Error Format

```json
{
  "errorMessage": "Error description"
}
```

### Error Examples

**Invalid parameters:**
```json
{
  "errorMessage": "Latitude is required"
}
```

**External service error:**
```json
{
  "errorMessage": "Internal error connecting to Google Places API. Please try again later."
}
```

## 🤝 Contributing

Contributions to the project are welcome! Please create an issue or pull request for improvement suggestions.
