export interface Menu {
  title: string;
  slug: string;
}

type Menus = Menu[];

export const menus: Menus = [
  {
    title: "Home",
    slug: "/",
  },
  {
    title: "Pricing",
    slug: "/pricing",
  },
  {
    title: "About",
    slug: "/about",
  },
  {
    title: "Contact",
    slug: "/contact",
  },
];
