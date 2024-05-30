import React from 'react';

const Navbar = () => {
  return (
    <nav className="flex justify-between w-full bg-gray-800">
      <div className="flex items-center">
        <a href="/" className="text-lg font-bold text-white hover:text-gray-300">
          ratr
        </a>
      </div>
      <ul className="flex justify-end">
        <li className="mr-6 font-bold text-white hover:text-gray-400">
          <NavLink to="/">home</NavLink>
        </li>
        <li className="mr-6 font-bold text-white hover:text-gray-400">
          <NavLink to="/films">films</NavLink>
        </li>
        <li className="mr-6 font-bold text-white hover:text-gray-400">
          <NavLink to="/login">login</NavLink>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
