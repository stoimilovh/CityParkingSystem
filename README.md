City Parking System is a project that demonstrates thread-safe operations, functional programming, and clean OOP design in a practical Java application. 
It includes classes like Parking, Floor, Vehicle, Ticket, and VehicleType (enum), each designed to be easily extendable for adding new features or vehicle types in the future. 
Vehicles are assigned to floors based on their type, with cars parked on the upper floors first so that trucks and buses, which can only use lower floors, always have available spots. 
Once the upper floors are full, cars start parking on the lower floors.
The system uses synchronization to safely handle multiple vehicles at the same time and Java lambda streams throughout the implementation for concise and efficient handling of collections..
Tickets track the real-time parking start and end times and calculate payments based on vehicle type.
The project also supports persistent storage with Gson, saving and loading parking data in JSON files to maintain state between program runs.
