module.exports = {
  prefix: '',
  purge: {
    // see: https://dev.to/angular/setup-tailwindcss-in-angular-the-easy-way-1i5l for purge only in prod
    enabled: true,
    content: [
      './src/**/*.{html,ts}',
    ]
  },
  darkMode: 'class', // or 'media' or 'class'
  theme: {
    extend: {},
  },
  variants: {
    extend: {},
  },
  plugins: [
    require('@tailwindcss/forms'),
    require('@tailwindcss/typography'),
  ],
};
