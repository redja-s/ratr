import { createBrowserRouter, RouterProvider } from "react-router-dom"
import Home from "./pages/Home.jsx";
import ErrorPage from "./pages/ErrorPage.jsx";
import ListFilms from "./pages/ListFilms.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/films",
    element: <ListFilms />,
    errorElement: <ErrorPage />,
  },
]);

function App() {
  return (
    <div className="bg-yellow-400 dark:bg-gray-900">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
