-- Insert sample pincode details
INSERT INTO pincode_details (pincode, latitude, longitude) VALUES
('411014', 18.5196, 73.8553),
('400001', 18.9750, 72.8258),
('110001', 28.6139, 77.2090);

-- Insert sample weather info
INSERT INTO weather_info (pincode, date, weather_data) VALUES
('411014', '2023-10-15', '{"coord":{"lon":73.8553,"lat":18.5196},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"base":"stations","main":{"temp":303.15,"feels_like":304.21,"temp_min":303.15,"temp_max":303.15,"pressure":1013,"humidity":54},"visibility":10000,"wind":{"speed":2.57,"deg":360},"clouds":{"all":0},"dt":1697377200,"sys":{"type":1,"id":9052,"country":"IN","sunrise":1697331234,"sunset":1697373456},"timezone":19800,"id":1259229,"name":"Pune","cod":200}'),
('400001', '2023-10-15', '{"coord":{"lon":72.8258,"lat":18.9750},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"base":"stations","main":{"temp":305.15,"feels_like":306.21,"temp_min":305.15,"temp_max":305.15,"pressure":1012,"humidity":52},"visibility":10000,"wind":{"speed":3.57,"deg":350},"clouds":{"all":20},"dt":1697377200,"sys":{"type":1,"id":9053,"country":"IN","sunrise":1697331234,"sunset":1697373456},"timezone":19800,"id":1275339,"name":"Mumbai","cod":200}');