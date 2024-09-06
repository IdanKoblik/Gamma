import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "Gamma",
  description: "Documentation website for Gamma api",
  logo: 'gamma.svg',
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' },
      { text: 'API', link: '/api' },
    ],

    sidebar: [
      {
        text: 'API',
        items: [
          { text: 'Game data api', link: '/api/game-data' }
        ]
      }
    ],

    socialLinks: [
        { icon: 'github', link: 'https://github.com/IdanKoblik/Gamma/' },
        { icon: 'discord', link: 'https://discord.gg/pN4acjE5'}
    ],

    footer: {
      message: "Released under the GNU gpl 3 License.",
      copyright: "Copyright © 2024-present Idan Koblik"
    },

    search: {
      provider: 'local'
    },
  }
})
