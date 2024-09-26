// eslint-disable-next-line @typescript-eslint/no-var-requires
const defaultTheme = require("tailwindcss/defaultTheme");
module.exports = {
  content: ["./src/**/*.tsx", "./src/**/*.css"],
  theme: {
    screens: {
      phone: {min: "375px", max: "719px"},
      // => @media (min-width: 375px) { ... }
      ...defaultTheme.screens,
      tablet: {min: "720px", max: "1279px"},
      // => @media (min-width: 720px) { ... }

      desktop: "1280px",
      // => @media (min-width: 1280px) { ... }
    },
    colors: {
      transparent: "transparent",
      current: "currentColor",
      white: {
        DEFAULT: "#ffffff",
      },
      black: {
        DEFAULT: "#000000",
        download: "#1E1B18",
        background: "#0f1114",
        15: "#181715",
        'F1': "#1F1F1F",
        13: "#131313",
        27: "#2B2927"
      },
      gray: {
        DEFAULT: "#E5E6EDA3",
        'A0': "#D4BBA0"
      },
      red: {
        DEFAULT: "#C6084A",
        hover: "#b20743",
        32: "#DA3232",
      },
      yellow: {
        '--pre-1': "#FFC530",
        '1F': "#F4921F",
        40: "#40301F"
      }
    },
    extend: {
      maxWidth: {
        75: "1208px",
        // 75 rem
      },
      backgroundImage: {
        "background-gradient":
          "linear-gradient(90deg, #1F1F1F 0%, #4E3B27 50%, #1F1F1F 100%)",
      },
      keyframes: {
        scaleIn: {
          '0%': {
            transform: 'scale(0.5)',
            opacity: '0',
          },
          '100%': {
            transform: 'scale(1)',
            opacity: '1',
          },
        },
        slideUp: {
          '0%': {transform: 'translateY(100%)'},
          '100%': {transform: 'translateY(0)'},
        },
      },
      animation: {
        scaleIn: 'scaleIn 0.3s ease-in-out forwards',
        slideUp: 'slideUp 0.4s ease-out',
      },
      typography: {
        DEFAULT: {
          css: {
            ul: {
              listStyleType: 'disc',
              marginLeft: '0.5em',
              marginBottom: '0.5em',
            },
            'ul ul': {
              listStyleType: 'circle',
            },
            'ul > li': {
              marginBottom: '0.5em',
            },
          },
        },
      },
    },
  },
  plugins: [require("@tailwindcss/forms")],
};
