import { createBrowserRouter } from "react-router-dom";

import Home from "./pages/Home.js";
import Explore from "./pages/Explore.js";

const AppRouter = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/explore",
    element: <Explore />,
  },
]);

export default AppRouter;
