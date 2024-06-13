import RatrLogo from '../logo.svg';
import { NavLink } from 'react-router-dom';
import DarkModeToggle from "./DarkModeToggle.jsx";
import "../css/Navbar.css";
import { IoHome } from "react-icons/io5";
import { IoFilm } from "react-icons/io5";

const Navbar = () => {
  return (
    <nav className="w-full fixed bg-white dark:bg-black items-center flex-wrap">
      <div className="max-w-screen-xl flex flex-wrap items-center justify-between">
        <div className="flex items-center flex-wrap space-x-5">
          <a href="/" className="flex items-center flex-wrap space-x-2">
            <img src={RatrLogo} className="h-8" alt="ratr" />
            <span className="font-extrabold px-0 mx-0 dark:text-white">
              ratr
            </span>
          </a>
        </div>
        <div className="justify-center border-red-600 hidden w-full bg-black dark:bg-black md:block md:w-auto" id="navbar-default">
          <ul className="flex flex-col md:p-0 font-bold mt-4 bg-black md:flex-row md:space-x-4 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-white dark:bg-black md:dark:bg-black dark:text-white">
            <li className="navbar-list-item">
              <DarkModeToggle />
            </li>
            <li className="navbar-list-item">
              <NavLink to="/">
                <IoHome size={20} />
              </NavLink>
            </li>
            <li className="navbar-list-item">
              <NavLink to="/films">
                <IoFilm size={20} />
              </NavLink>
            </li>
            <li>
              <NavLink to="/login" className="flex items-center p-2 rounded-lg bg-blue-400 hover:bg-blue-500 text-white dark:text-white">
                Sign In
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
