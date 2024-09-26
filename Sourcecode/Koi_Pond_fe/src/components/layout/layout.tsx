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
      <Footer />
    </div>
  );
};
export default Layout;
