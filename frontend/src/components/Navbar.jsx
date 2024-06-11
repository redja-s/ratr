import RatrLogo from '../logo.svg';
import { NavLink } from 'react-router-dom';
import DarkModeToggle from "./DarkModeToggle.jsx";
import "../css/Navbar.css";
import { IoHome } from "react-icons/io5";
import { IoLogIn } from "react-icons/io5";
import { IoFilm } from "react-icons/io5";

const Navbar = () => {
  return (
    <header className="h-[95px]">
      <nav className="w-full bg-white dark:bg-black">
        <div className="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
          <div className="flex items-center flex-wrap space-x-5">
            <div className="flex items-center space-x-3 rtl:space-x-reverse">
              <a href="/">
                <img src={RatrLogo} className="h-8" alt="ratr" />
                <span className="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">ratr</span>
              </a>
              <DarkModeToggle />
            </div>
          </div>
          <button data-collapse-toggle="navbar-default" type="button" className="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600" aria-controls="navbar-default" aria-expanded="false">
            <span className="sr-only">Open main menu</span>
            <svg className="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
              <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M1 1h15M1 7h15M1 13h15" />
            </svg>
          </button>
          <div className="hidden w-full bg-black dark:bg-black md:block md:w-auto" id="navbar-default">
            <ul className="flex flex-col md:p-0 mt-4 bg-black md:flex-row md:space-x-4 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-white dark:bg-black md:dark:bg-black dark:text-white">
              <li className="navbar-list-item">
                <DarkModeToggle />
              </li>
              <li className="navbar-list-item">
                <NavLink to="/">
                  <span><IoHome /></span>
                </NavLink>
              </li>
              <li className="navbar-list-item">
                <NavLink to="/films"><IoFilm /></NavLink>
              </li>
              <li className="navbar-list-item text-xs">
                <NavLink to="/login">
                  <IoLogIn /> Sign In
                </NavLink>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
  );
};

export default Navbar;
