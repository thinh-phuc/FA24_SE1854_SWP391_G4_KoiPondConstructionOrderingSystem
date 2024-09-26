import { useState, useEffect } from "react";
import { menus, Menu } from "components/layout/menus";
import Typography from "components/typography/index";
import Logo from "assets/images/Logo.png"
import menu from "assets/icons/Menu.svg"

const Header = () => {
  const [isOpen, setIsOpen] = useState(false);

  const handleScrollToSection = (sectionId: string) => {
    const section = document.getElementById(sectionId);
    if (section) {
      section.scrollIntoView({ behavior: "smooth" });
      setTimeout(() => {
        window.location.hash = sectionId;
      }, 800);
    }
  };

  const handleMenuClick = (slug: string) => {
    if (slug === "/") {
      window.scrollTo({ top: 0, behavior: "smooth" });
      setTimeout(() => {
        window.location.hash = "";
      }, 1);
    } else {
      const sectionId = slug.replace("/", "");
      handleScrollToSection(sectionId);
    }
    setIsOpen(false);
  };

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };
  useEffect(() => {
    const hash = window.location.hash.replace("#", "");
    hash &&
      setTimeout(() => {
        handleScrollToSection(hash);
      }, 1);
  }, []);
  return (
    <header className="w-full sticky top-0 px-[45px] flex justify-between items-center bg-white h-[130px] phone:h-[48px] phone:px-[15px] tablet:px-[30px] z-[100]">
      <div className="flex items-end justify-between gap-2">
        <a href="/">
          <img
            src={Logo}
            alt="Logo"
            className="h-[130px] w-[150px]"
          />
        </a>
      </div>
      <div className="hidden desktop:flex items-center gap-7 space-x-10 text-black font-bold text-[24px] leading-[29px]">
        {menus.map((item: Menu, key) => (
          <Typography
            key={key}
            className="cursor-pointer font-bold hover:text-pink-second_02"
            onClick={() => handleMenuClick(item.slug)}
          >
            {item.title}
          </Typography>
        ))}
      </div>
      <div className="flex phone:hidden tablet:hidden justify-center items-center w-10 leading-[17px]">
      
      </div>
      <div className="relative desktop:hidden">
        <button
          className="flex desktop:hidden justify-center items-center w-10 h-10 z-20"
          onClick={toggleDropdown}
        >
          <img src={menu} alt="Menu" width="24" height="19" />
        </button>
        {isOpen && (
          <div className="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg z-50">
            <div className="py-1">
              {menus.map((item: Menu, key) => (
                <div
                  key={key}
                  className="cursor-pointer px-4 py-2 text-gray-800 hover:bg-gray-200 font-montserrat-normal"
                  onClick={() => handleMenuClick(item.slug)}
                >
                  {item.title}
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;
