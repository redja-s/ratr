import React from "react";
import { RiMoonFill } from "react-icons/ri";
import { RiSunFill } from "react-icons/ri";

const DarkModeToggle = () => {
  const [darkMode, setDarkMode] = React.useState(false);

  const darkModeHandler = () => {
    setDarkMode(!darkMode);
    document.body.classList.toggle("dark");
  }

  return (
    <button onClick={() => darkModeHandler()}>
      {darkMode && <RiSunFill size={20} />}
      {!darkMode && <RiMoonFill size={20} />}
    </button>
  )
}

export default DarkModeToggle;
