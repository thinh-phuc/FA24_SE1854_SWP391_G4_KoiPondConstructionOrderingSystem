import React, { useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { AiOutlineLoading3Quarters } from "react-icons/ai";
import "../../index.css";

const LoginPage = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  const [errors, setErrors] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    setErrors({ ...errors, [name]: "" });
  };

  const validateForm = () => {
    let isValid = true;
    const newErrors = { username: "", password: "" };

    if (!formData.username.trim()) {
      newErrors.username = "Username or Email is required";
      isValid = false;
    }

    if (!formData.password) {
      newErrors.password = "Password is required";
      isValid = false;
    } else if (formData.password.length < 6) {
      newErrors.password = "Password must be at least 6 characters long";
      isValid = false;
    }

    setErrors(newErrors);
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      setLoading(true);
      // Simulating API call
      setTimeout(() => {
        setLoading(false);
        alert("Login successful!");
      }, 2000);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue to-green flex items-center justify-center p-4">
      <div className="max-w-md w-full bg-white rounded-lg shadow-xl overflow-hidden">
        <div className="bg-blue text-white py-4 px-6 text-center">
          <h2 className="text-2xl font-bold ">Koi Pond Construction</h2>
          <p className="text-blue">Login to your account</p>
        </div>
        <form onSubmit={handleSubmit} className="p-6 space-y-6">
          <div>
            <label htmlFor="username" className="text-grayfont-semibold block mb-2">
              Username or Email
            </label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 transition-colors ${errors.username ? "border-red focus:ring-red" : "border-gray focus:ring-blue"}`}
              aria-invalid={errors.username ? "true" : "false"}
              aria-describedby="username-error"
            />
            {errors.username && (
              <p id="username-error" className="mt-1 text-red text-sm">
                {errors.username}
              </p>
            )}
          </div>
          <div>
            <label htmlFor="password" className="text-grayfont-semibold block mb-2">
              Password
            </label>
            <div className="relative">
              <input
                type={showPassword ? "text" : "password"}
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 transition-colors ${errors.password ? "border-red focus:ring-red" : "border-gray focus:ring-blue"}`}
                aria-invalid={errors.password ? "true" : "false"}
                aria-describedby="password-error"
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray focus:outline-none"
                aria-label={showPassword ? "Hide password" : "Show password"}
              >
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </button>
            </div>
            {errors.password && (
              <p id="password-error" className="mt-1 text-red text-sm">
                {errors.password}
              </p>
            )}
          </div>
          <div>
            <button
              type="submit"
              disabled={loading}
              className={`w-full bg-blue text-white font-semibold py-2 px-4 rounded-md hover:bg-blue focus:outline-none focus:ring-2 focus:ring-blue focus:ring-opacity-50 transition-colors ${loading ? "opacity-75 cursor-not-allowed" : ""}`}
            >
              {loading ? (
                <>
                  <AiOutlineLoading3Quarters className="animate-spin inline-block mr-2" />
                  Logging in...
                </>
              ) : (
                "Login"
              )}
            </button>
          </div>
          <div className="text-center">
            <a href="#" className="text-blue hover:underline focus:outline-none focus:ring-2 focus:ring-bluefocus:ring-opacity-50 rounded-sm">
              Forgot password?
            </a>
          </div>
        </form>
        <div className="bg-gray py-4 px-6 text-center">
          <p className="text-gray-600">
            Don't have an account?{" "}
            <a href="#" className="text-blue font-semibold hover:underline focus:outline-none focus:ring-2 focus:ring-blue focus:ring-opacity-50 rounded-sm">
              Sign up
            </a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;