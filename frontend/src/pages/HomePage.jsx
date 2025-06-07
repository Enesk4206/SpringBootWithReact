import React from 'react'
import Hero from '../components/Hero'
import AutoSlider from '../components/Slidebar'
import Sliderbar from '../components/Slidebar'
import Navbar from '../components/Navbar'

const products = [
  { id: 1, name: 'Ürün 1', price: '₺100', image: 'https://via.placeholder.com/150' },
  { id: 2, name: 'Ürün 2', price: '₺200', image: 'https://via.placeholder.com/150' },
  { id: 3, name: 'Ürün 3', price: '₺300', image: 'https://via.placeholder.com/150' },
  { id: 4, name: 'Ürün 4', price: '₺400', image: 'https://via.placeholder.com/150' },
  { id: 5, name: 'Ürün 5', price: '₺500', image: 'https://via.placeholder.com/150' },
  { id: 6, name: 'Ürün 6', price: '₺600', image: 'https://via.placeholder.com/150' },
]

const ProductCard = ({ product }) => (
  <div className="w-40 bg-white rounded shadow p-3">
    <img src={product.image} alt={product.name} className="w-full h-32 object-cover rounded" />
    <h3 className="mt-2 font-semibold text-sm">{product.name}</h3>
    <p className="text-blue-600 font-bold text-sm">{product.price}</p>
  </div>
)

const HomePage = () => {
  return (
    <div className="min-h-screen flex flex-col">

      {/* Hero */}
      <Hero />

      {/* Slider */}
      <section className="bg-gray-100 py-10 px-6">
        <h2 className="text-2xl font-semibold mb-4">Fırsat Ürünlerini Kaçırmayın</h2>
        <Sliderbar>
          {products.map(product => (
            <ProductCard key={product.id} product={product} />
          ))}
        </Sliderbar>
      </section>
  {/* Popüler Ürünler */}
<section className="bg-white py-10 px-6">
  <div className="max-w-7xl mx-auto">
    <h2 className="text-3xl font-bold mb-6">Popüler Ürünler</h2>

    <div className="relative">
      <div className="flex space-x-4 overflow-x-auto scrollbar-hide h-[400px] pb-4">
        {products.map(product => (
          <div key={product.id} className="flex-none w-72">
            <ProductCard product={product} />
          </div>
        ))}
      </div>
    </div>
  </div>
</section>
    </div>
  )
}

export default HomePage
