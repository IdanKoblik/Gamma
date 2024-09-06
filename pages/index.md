---
# https://vitepress.dev/reference/default-theme-home-page
layout: home

hero:
  name: "Gamma"
  text: "Spigot minigame api"
  tagline: Official documentation
  image:
    src: /assets/gamma.svg
    alt: VitePress
    
  actions:
    - theme: brand
      text: API documentation
      link: /api
    - theme: alt
      text: download
      link: /

features:
  - title: Game State Management.
    details: Provides functionality to manage and track the various states of minigames such as waiting, in-progress, and completed. Developers can easily start, pause, and stop games, and query their current status. This feature ensures consistent handling of game rules and transitions.
  - title: Player Management and Integration
    details: Offers tools for managing player interactions within the minigame, including player events, team assignments, and player-specific data like scores and inventories. This feature enhances player experience by allowing custom roles and tracking individual player metrics.
  - title: Customizable Game Rules and Configurations
    details: Allows developers to define and adjust game rules and settings through a flexible system, such as configuration files or in-game commands. This includes parameters like game duration, win conditions, and specific mechanics, enabling diverse and customizable minigame experiences.
---
