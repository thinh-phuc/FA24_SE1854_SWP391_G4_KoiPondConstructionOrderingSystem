import React from 'react';

const Footer = () => {
  return (
    <footer className="text-white py-8">
      <div className="container mx-auto text-center">
        <div className="flex justify-center space-x-6 mb-4">
          <a href="https://twitter.com" className="text-black hover:text-opacity-40">
            <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 24 24">
              <path d="M24 4.557a9.93 9.93 0 01-2.828.775 4.932 4.932 0 002.165-2.724 9.864 9.864 0 01-3.127 1.195 4.92 4.92 0 00-8.384 4.482A13.978 13.978 0 011.671 3.149a4.92 4.92 0 001.523 6.573 4.903 4.903 0 01-2.229-.616c-.054 2.281 1.581 4.415 3.949 4.89a4.935 4.935 0 01-2.224.084 4.928 4.928 0 004.604 3.417A9.867 9.867 0 010 21.543a13.94 13.94 0 007.548 2.212c9.057 0 14.01-7.496 14.01-13.986 0-.213-.005-.425-.014-.636A10.025 10.025 0 0024 4.557z" />
            </svg>
          </a>
          <a href="https://linkedin.com" className="text-black hover:text-opacity-40">
            <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 24 24">
              <path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.761 0 5-2.239 5-5v-14c0-2.761-2.239-5-5-5zm-11 19h-3v-10h3v10zm-1.5-11.268c-.966 0-1.75-.784-1.75-1.75s.784-1.75 1.75-1.75 1.75.784 1.75 1.75-.784 1.75-1.75 1.75zm13.5 11.268h-3v-5.5c0-1.378-.028-3.152-1.92-3.152-1.92 0-2.214 1.5-2.214 3.052v5.6h-3v-10h2.88v1.367h.041c.401-.76 1.379-1.56 2.84-1.56 3.04 0 3.6 2 3.6 4.6v5.593z" />
            </svg>
          </a>
          <a href="https://facebook.com" className="text-black hover:text-opacity-40">
            <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 24 24">
              <path d="M22.675 0h-21.35c-.733 0-1.325.592-1.325 1.325v21.351c0 .733.592 1.324 1.325 1.324h11.495v-9.294h-3.125v-3.622h3.125v-2.671c0-3.1 1.893-4.788 4.659-4.788 1.325 0 2.463.099 2.795.143v3.24h-1.918c-1.504 0-1.794.715-1.794 1.763v2.313h3.587l-.467 3.622h-3.12v9.294h6.116c.733 0 1.325-.591 1.325-1.324v-21.351c0-.733-.592-1.325-1.325-1.325z" />
            </svg>
          </a>
        </div>
        <div className="flex justify-center space-x-8 mb-4">
          <div className="text-black flex items-center space-x-1">
            <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 384 512">
              <path d="M215.7 499.2C267 435 384 279.4 384 192C384 86 298 0 192 0S0 86 0 192c0 87.4 117 243 168.3 307.2c12.3 15.3 35.1 15.3 47.4 0zM192 128a64 64 0 1 1 0 128 64 64 0 1 1 0-128z" />
            </svg>
            <p>Grass Roots Company, 123 East, 17th Street, Ho Chi Minh City</p>
          </div>
          <div className="text-black">
            <p>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</p>
          </div>
          <div className="text-black">
            <p>When an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
          </div>
        </div>
        <div className="text-black">
          © 2024 Grass Roots Rights Reserved.
        </div>
      </div>
    </footer>
  );
};

export default Footer;
