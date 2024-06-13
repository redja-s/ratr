const Banner = () => {
  const bannerHeader = "Track ratings for films!";
  const bannerSubtext = "ratr provides an all-in-one solution for tracking everything you watch!"

  return (
    <div className="text-center">
      <h1 className="mb-6 text-4xl font-extrabold leading-none tracking-tight md:text-5xl lg:text-6xl dark:text-white">{bannerHeader}</h1>
      <p className="mb-6 text-lg font-normal text-black lg:text-xl sm:px-16 xl:px-48 dark:text-white">
        {bannerSubtext}
      </p>
      <a href="/login" className="font-extrabold inline-flex items-center justify-center px-5 py-3 text-base text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 dark:focus:ring-blue-900">
        Create Account
        <svg className="w-3.5 h-3.5 ms-2 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
          <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M1 5h12m0 0L9 1m4 4L9 9" />
        </svg>
      </a>
    </div>
  )
}

export default Banner;
