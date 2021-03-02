module.exports = {
  prefix: '',
  purge: {
    // see: https://dev.to/angular/setup-tailwindcss-in-angular-the-easy-way-1i5l for purge only in prod
    // enabled: true,
    content: [
      './src/**/*.{html,ts}',
    ]
  },
  darkMode: 'class', // or 'media' or 'class'
  theme: {
    colors: {
      // Build your palette here
      transparent: 'transparent',
      current: 'currentColor',
      background: '#091421',
      foreground: '#E7E7E7',
      yellow: '#F89F00',
    },
  },
  extend: {
    typography: (theme) => {
      return {
        DEFAULT: {
          css: {
            h1: {
              color: theme('colors.foreground'),
            },
            h2: {
              color: theme('colors.foreground'),
            },
            h3: {
              color: theme('colors.foreground'),
            },
            h4: {
              color: theme('colors.foreground'),
            },
            p: {
              color: theme('colors.foreground'),
            },
            strong: {
              color: 'inherit',
            },
          },
        },
      };
    },
  },
    variants: {
    extend: {},
  },
  plugins: [
    require('@tailwindcss/forms'),
    require('@tailwindcss/typography'),
  ],
};
