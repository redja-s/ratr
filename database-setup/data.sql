INSERT INTO public.films(
  title,
  director_name,
  release_year,
  description,
  cover_image_path
)VALUES
('The Matrix Resurrections', 'Lana Wachowski', 2022, 'The fourth installment in the Matrix franchise', 'https://upload.wikimedia.org/wikipedia/en/5/50/The_Matrix_Resurrections.jpg'),
('Spider-Man: No Way Home', 'Jon Watts', 2021, 'The third installment in the Marvel Cinematic Universe Spider-Man series', 'https://upload.wikimedia.org/wikipedia/en/0/00/Spider-Man_No_Way_Home_poster.jpg'),
('The Batman', 'Matt Reeves', 2022, 'A reboot of the Batman film series', 'https://upload.wikimedia.org/wikipedia/en/f/ff/The_Batman_%28film%29_poster.jpg'),
('Doctor Strange in the Multiverse of Madness', 'Sam Raimi', 2022, 'The sequel to Doctor Strange', 'https://upload.wikimedia.org/wikipedia/en/1/17/Doctor_Strange_in_the_Multiverse_of_Madness_poster.jpg'),
('The French Dispatch', 'Wes Anderson', 2021, 'A comedy-drama film set in a fictional French city', 'https://upload.wikimedia.org/wikipedia/en/7/78/The_French_Dispatch.jpeg'),
('No Time to Die', 'Cary Joji Fukunaga', 2021, 'The 25th James Bond film', 'https://upload.wikimedia.org/wikipedia/en/f/fe/No_Time_to_Die_poster.jpg'),
('Shang-Chi and the Legend of the Ten Rings', 'Destin Daniel Cretton', 2021, 'A superhero film based on the Marvel Comics character Shang-Chi', 'https://upload.wikimedia.org/wikipedia/en/7/74/Shang-Chi_and_the_Legend_of_the_Ten_Rings_poster.jpeg'),
('The Power of the Dog', 'Jane Campion', 2021, 'A drama film set in 1920s Montana', 'https://upload.wikimedia.org/wikipedia/en/6/6d/The_Power_of_the_Dog_%28film%29.jpg'),
('West Side Story', 'Steven Spielberg', 2021, 'A musical romantic drama film', 'https://upload.wikimedia.org/wikipedia/en/2/2e/West_Side_Story_2021_Official_Poster.jpg'),
('Encanto', 'Bryce Dallas Howard', 2021, 'An animated musical fantasy film', 'https://upload.wikimedia.org/wikipedia/en/8/83/Encanto_poster.jpg')
ON CONFLICT (title, director_name, release_year) DO NOTHING;
