import Trip from "./trip.js";
import Day from "./day.js";
import Place from "./Place.js";

export default class TripAdapter {
  places = [];
  days = [];
  trips = [];

  constructor() {
    this.baseUrl = "http://localhost:8080/trips";
  }

  fetchTrips() {
    fetch(this.baseUrl)
      .then((res) => {
        if (!res.ok) {
          throw new Error(`HTTP error! status: ${res.status}`);
        }
        return res.json();
      })
      .then(data => {
        this.updateState(data); // Update state with the fetched data
      })
      .catch((err) => alert(err));
  }

  newTrip(tripData) {
    console.log("Trip Data:" , tripData.trip);
    console.log("days_attributes from newTrip:",tripData.trip.days_attributes);
    const tripPayload = {
        city: tripData.trip.city,
        lat: tripData.trip.lat,
        lng: tripData.trip.lng,
        days_attributes: tripData.trip.days_attributes
    };
    console.log("Trip Payload:", JSON.stringify(tripPayload));
      fetch(this.baseUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(tripPayload)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to save trip");
        }
        return response.text();
    })
    .then(data => {
        console.log("Trip saved:", data);
        alert('Trip saved successfully. Check existing itineraries.');
        // Fetch updated trip data here
    })
    .catch(error => console.error("Error:", error));
  }



updateState(json) {
  if (!json.included) {
      console.error("json.included is undefined");
      return;
  }
  const placeObjs = json.included.filter((obj) => obj.type === "place");
  placeObjs.forEach((placeObj) => this.addPlace(placeObj));

  const dayObjs = json.included.filter((obj) => obj.type === "day");
  dayObjs.forEach((dayObj) => this.addDay(dayObj));

  // Log days to verify they're being added
  console.log("Current Days:", this.days);

  json.data.forEach(
      (tripData) =>
          new Trip({ ...tripData.attributes, id: tripData.id, days: this.days })
  );
}

  addDay(data) {
    this.days.push(
      new Day({
        id: data.id,
        date: new Date(data.attributes.date.replace(/-/g, "/")),
        trip_id: data.relationships.trip.data.id,
        places: this.places,
      })
    );
  }

  addPlace(data) {
    this.places.push(
      new Place({
        name: data.attributes.name,
        place_id: data.attributes.place_id,
        type: data.attributes.category,
        id: data.id,
        day_id: data.relationships.day.data.id,
      })
    );
  }
}
