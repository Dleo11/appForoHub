ALTER TABLE cursos
ADD descripcion TEXT;

UPDATE cursos SET descripcion = "Un curso introductorio a Python, cubriendo desde la instalación hasta la creación de scripts básicos y manejo de datos." WHERE nombre = 'Introduction to Python';
UPDATE cursos SET descripcion = "Domina la creación de sitios web con HTML y CSS, aprendiendo sobre diseño responsivo y buenas prácticas de desarrollo." WHERE nombre = 'Web Development with HTML & CSS';
UPDATE cursos SET descripcion = "Conoce los fundamentos de JavaScript, incluyendo manipulación del DOM, eventos y programación asincrónica." WHERE nombre = 'JavaScript Essentials';
UPDATE cursos SET descripcion = "Aprende a gestionar bases de datos relacionales utilizando SQL, desde consultas básicas hasta operaciones avanzadas." WHERE nombre = 'Database Management with SQL';
UPDATE cursos SET descripcion = "Explora las estructuras de datos y algoritmos más comunes, y cómo aplicarlos para resolver problemas complejos." WHERE nombre = 'Data Structures and Algorithms';
UPDATE cursos SET descripcion = "Desarrolla aplicaciones móviles multiplataforma utilizando Flutter y Dart, desde la interfaz de usuario hasta la lógica de negocio." WHERE nombre = 'Mobile App Development with Flutter';
UPDATE cursos SET descripcion = "Introduce los principios de DevOps, incluyendo integración continua, entrega continua y automatización de infraestructuras." WHERE nombre = 'DevOps Fundamentals';
UPDATE cursos SET descripcion = "Descubre los conceptos básicos del aprendizaje automático, incluyendo algoritmos supervisados y no supervisados." WHERE nombre = 'Machine Learning Basics';
UPDATE cursos SET descripcion = "Aprende los fundamentos de la ciberseguridad, incluyendo técnicas de protección de datos y análisis de vulnerabilidades." WHERE nombre = 'Cybersecurity Essentials';
UPDATE cursos SET descripcion = "Aprende los conceptos básicos de Java, incluyendo sintaxis, estructuras de control y programación orientada a objetos." WHERE nombre = 'Java Fundamentals';
