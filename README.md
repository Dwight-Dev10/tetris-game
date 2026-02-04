# 🎮 Java Tetris(Swing)

A fully playable Tetris clone built in Java using Swing, designed with object-oriented principles, a custom game loop, and clean separation between rendering, game logic, input, and audio systems.

This project was built to strengthen Java fundamentals, explore OOP design, and practice system-level thinking through a complete, shippable application.

✨ Features:
  🧱 Classic Tetris gameplay
  🔄 Piece rotation with collision handling
  ⬇️ Gravity-based drop system with frame timing
  ⌨️ Keyboard input handling
  ⏸️ Pause functionality
  🔊 Sound effects and background music
  🧠 Next-piece preview
  🧩 Line clearing & stacking logic
  🖼️ Custom rendering using Graphics2D

# 🏗️ Architecture Overview

The project is structured to keep responsibilities clear and maintainable:

**Core Components**

-GamePanel
 - Main rendering surface
 - Owns the game loop (FPS + delta timing)
 - Delegates updates and drawing

- PlayManager
 - Central game controller
 - Manages game state, piece lifecycle, and rules
 - Coordinates current piece, next piece, and static blocks

- Arks_main
 - Base class for all Tetris pieces
 - Handles movement, rotation, collision detection
 - Uses inheritance for shape-specific behavior

- Block
  - Represents a single tile unit
  - Encapsulates position, size, and color

- KeyHandler
 - Captures and processes player input
 - Decoupled from game logic

- Sound
 - Handles audio playback and looping
 - Loads resources from the classpath
 - Manages sound effects and music independently





