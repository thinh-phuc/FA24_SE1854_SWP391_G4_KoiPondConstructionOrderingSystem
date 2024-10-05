import { ReactNode } from "react";
import Header from "./header.tsx";
import Footer from "./footer.tsx";

interface LayoutProps {
  children: ReactNode;
}

const Layout: React.FC<LayoutProps> = ({ children }) => {
  return (
    <div className="flex flex-col relative">
      <Header />
      {children}
      <hr className="border-t-2 border-gray my-4" />
      <Footer />
    </div>
  );
};
export default Layout;
