import React from 'react'

const Hero = () => {
  return (
       <section className="w-full bg-gradient-to-r from-blue-600 to-indigo-700 text-white py-20 px-6 text-center">
      <h1 className="text-5xl font-extrabold mb-4">
        Merhaba, Hoşgeldiniz!
      </h1>
      <p className="text-xl max-w-3xl mx-auto mb-8">
        Fırsat ürünlerimizi kaçırmayın! Sadece sınırlı süre için özel indirimler sizi bekliyor.
      </p>
      <button className="bg-white text-blue-700 font-bold px-10 py-4 rounded-md shadow hover:bg-gray-100 transition">
        Hemen Alışverişe Başla
      </button>
    </section>
  )
}

export default Hero