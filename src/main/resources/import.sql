INSERT INTO team ("id", "name") VALUES
  (1, 'Team Alpha'),
  (2, 'Team Bravo'),
  (3, 'Team Charlie'),
  (4, 'Team Delta');

INSERT INTO person ("id", "address", "email", "firstname", "lastname", "team_id") VALUES
  (1, NULL, 'luke.skywalker@example.com', 'Luke', 'Skywalker', 1),
  (2, NULL, 'leia.organa@example.com', 'Leia', 'Organa', 1),
  (3, NULL, 'anakin.skywalker@example.com', 'Anakin', 'Skywalker', 2),
  (4, NULL, 'obi-wan.kenobi@example.com', 'Obi-Wan', 'Kenobi', 2),
  (5, NULL, 'han.soloe@example.com', 'Han', 'Solo', 3),
  (6, NULL, 'boba.fett@example.com', 'Boba', 'Fett', 3),
  (7, NULL, 'padme.amidala@example.com', 'Padme', 'Amidala', 4),
  (8, NULL, 'jar.jar.binks@example.com', 'Jar Jar', 'Binks', 4),
  (9, NULL, 'admiral.ackbar@example.com', 'Admiral', 'Ackbar', NULL),
  (10, NULL, 'general.grievous@example.com', 'General', 'Grievous', NULL);

INSERT INTO competition ("id", "name", "leader_id", "president_id") VALUES
  (1, 'ACM-ICPC', 9, 10);
