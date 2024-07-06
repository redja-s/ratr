import "../css/Banner.css";
import { useEffect, useRef, useState } from "react";

const Banner = () => {
  const bannerHeader1 = "Track all the films you watch";
  const bannerHeader2 = "Tell your friends about them";
  const bannerSubtext = "ratr is the platform for sharing media you love"
  const buttonText = "Get started - it's free!"
  const bannerRef = useRef(null);

  useEffect(() => {
    const img = new Image();
    img.src = 'https://miro.medium.com/v2/resize:fit:1400/1*0RF4HabtzYE_TyjCaNVh5g.jpeg';

    img.onload = () => {
      if (bannerRef.current) {
        bannerRef.current.style.height = `${img.height}px`;
      }
    };
  }, []);

  return (
    <div ref={bannerRef} className="banner justify-center flex border border-red-500 p-0">
      <div className="border border-red-500 text-center text-white">
        <h1 className="font-extrabold text-2xl mb-6">
          <p className="py-1">{bannerHeader1}</p>
          <p className="py-1">{bannerHeader2}</p>
        </h1>
        <div className="mb-6">
          <a href="/login" className="bg-green-600 rounded-lg p-1 hover:bg-green-700">
            {buttonText}
          </a>
        </div>
        <div>
          <p className="text-gray-400">{bannerSubtext}</p>
        </div>
      </div>
    </div>
  )
}

export default Banner;
