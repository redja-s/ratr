import RatrLogo from "../logo.svg";
import { NavLink } from "react-router-dom";
import "../css/Navbar.css";

const Navbar = () => {
  return (
    <nav className="bg-black text-white text-sm">
      <div className="flex items-center flex-wrap py-4 pl-6">
        <a href="/" className="flex items-center flex-wrap pr-4 space-x-2">
          <img src={RatrLogo} className="h-8" alt="ratr" />
          <span>ratr</span>
        </a>
        <ul className="flex pl-5 items-center space-x-6 border-l border-slate-200">
          <li className="navbar-list-item">
            <NavLink to="/">Home</NavLink>
          </li>
          <li className="navbar-list-item">
            <NavLink to="/films">Films</NavLink>
          </li>
          <li className="navbar-list-item">
            <NavLink to="/login" className="p-1 border border-white">
              Sign In
            </NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
