import Navbar from "../components/Navbar.jsx";
import ErrorMessage from "../components/ErrorMessage.jsx";

const ErrorPage = () => {
  return (
    <div className="page-default-width">
      <Navbar />
      <ErrorMessage />
    </div >
  );
}

export default ErrorPage;
