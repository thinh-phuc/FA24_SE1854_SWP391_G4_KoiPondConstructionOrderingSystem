import main_img from "assets/images/nhatban.jpg";

const MainHome = () => {
  return (
    <div
      className="relative w-full h-screen bg-cover bg-center"
      style={{ backgroundImage: `url(${main_img})` }}
    >
      <div className="absolute inset-0 bg-black opacity-60"></div>
      <div className="relative flex flex-col items-center justify-center h-full text-center text-white px-4">
        <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold leading-tight">
          Pond Construction In Ann Arbor, Michigan, And Surrounding Areas
        </h1>
        <p className="mt-4 text-lg md:text-xl lg:text-2xl">
          Change The Way You Spend Time In Your Backyard With A Custom Pond
        </p>
       
      </div>
    </div>
  );
};

export default MainHome;
