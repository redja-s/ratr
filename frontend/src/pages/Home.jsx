import Navbar from "../components/Navbar.jsx";
import Banner from "../components/Banner.jsx";
import FeaturedFilms from "../components/FeaturedFilms.jsx";
import "../css/Global.css";

const Home = () => {
  return (
    <>
      <Navbar />
      <div className="page-base">
        <Banner />
        <FeaturedFilms />
      </div>
    </>
  );
}

export default Home;
