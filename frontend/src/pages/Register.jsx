import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../api/Api';

const RegisterPage = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleRegister = async (e) => {
    e.preventDefault();

    try {
      await api.post("/auth/register",{
        username,email,password
      });
      alert("Kayıt Başarılı");
    } catch (error) {
      console.error("Kayıt hatası:", error);
      alert("Kayıt başarısız!");
    }

    // Burada kayıt işlemini yap
    console.log("Register:", username, email, password);
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded shadow-md w-full max-w-md">
        <h2 className="text-2xl font-bold mb-6 text-center">Kayıt Ol</h2>
        <form onSubmit={handleRegister}>
          <input
            type="text"
            placeholder="Kullanıcı Adı"
            className="w-full p-2 border border-gray-300 rounded mb-4"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            type="email"
            placeholder="Email"
            className="w-full p-2 border border-gray-300 rounded mb-4"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Şifre"
            className="w-full p-2 border border-gray-300 rounded mb-4"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
          >
            Kayıt Ol
          </button>
        </form>
        <p className="mt-4 text-sm text-center">
          Zaten hesabınız var mı?{" "}
          <Link to="/login" className="text-blue-600 hover:underline">
            Giriş yapın
          </Link>
        </p>
      </div>
    </div>
  );
};

export default RegisterPage;
