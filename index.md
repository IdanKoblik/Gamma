---
# https://vitepress.dev/reference/default-theme-home-page
layout: home

hero:
  name: "Gamma"
  text: "Spigot minigame api"
  tagline: Official documentation
  image:
    src: https://raw.githubusercontent.com/IdanKoblik/Gamma/pages/img/gamma.svg
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
<script setup>
import {
  VPTeamPage,
  VPTeamPageTitle,
  VPTeamMembers
} from 'vitepress/theme';

const member = [
  {
    avatar: 'https://www.github.com/IdanKoblik.png',
    name: 'Idan Koblik',
    title: 'Maintainer',
    links: [
      { icon: 'github', link: 'https://github.com/IdanKoblik' },
      { icon: 'discord', link: 'https://discord.com/users/429212281914785793' },
      { icon: 'linkedin', link: 'https://www.linkedin.com/in/idan-k/' }
    ]
  },
]
</script>
<center>
    <VPTeamPageTitle>
        <template #title>Our Team</template>
        <template #lead>...</template>
    </VPTeamPageTitle>
    <VPTeamPageSection>
          <VPTeamMembers
            size="medium" :members="member"
          />
    </VPTeamPageSection>
</center>
