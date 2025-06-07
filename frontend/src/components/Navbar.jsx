import React from 'react'
import { Link } from 'react-router-dom'

const Navbar = () => {
  return (
    <nav className="bg-blue-600 text-white shadow-md w-full">
  <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
    <div className="flex justify-between items-center py-4">
      
      {/* Logo */}
      <div className="flex-shrink-0 text-gray-20 text-2xl font-extrabold tracking-tight">
        My E-Commerce
      </div>

      {/* Menü Butonları */}
      <div className="hidden md:flex space-x-4">
        <Link to={"/login"}>
         <button className="px-4 py-2 rounded-md bg-blue-600 text-white font-medium hover:bg-blue-700 transition duration-200">
          Giriş Yap
        </button>
        </Link>
       <Link to={"/register"}>
         <button className="px-4 py-2 rounded-md bg-blue-100 text-blue-700 font-medium hover:bg-blue-200 transition duration-200">
          Kayıt Ol
        </button>
       </Link>
       
        <button className="px-4 py-2 rounded-md bg-blue-50 text-blue-700 font-medium hover:bg-blue-100 transition duration-200">
          Sepet (0)
        </button>
      </div>

      {/* Mobil Menü Placeholder (ileride kullanılabilir) */}
      <div className="md:hidden">
        {/* Menü ikonu buraya eklenebilir */}
      </div>
    </div>
  </div>
</nav>

  )
}

export default Navbar