import { useRouteError } from "react-router-dom";
import Navbar from "../components/Navbar.jsx";

const ErrorPage = () => {
  const error = useRouteError();

  return (
    <div>
      <Navbar />
      <h1>Our website broke :/</h1>
      <p><i>{error.statusText || error.message}</i></p>
    </div>
  );
}

export default ErrorPage;
