import { useRouteError } from "react-router-dom";
const ErrorMessage = () => {
  const errorMessage = "Something went wrong!";
  const error = useRouteError();

  return (
    <div className="text-black dark:text-white mx-auto text-center w-2/3 p-5 md:p-5 lg:p-10">
      <h1 className="flex font-extrabold text-2xl items-center justify-center">{errorMessage}</h1>
      <p><i>{error.statusText || error.message}</i></p>
    </div>
  )
}
export default ErrorMessage;
