import CategoryPage from './pages/Categorypages'
import {BrowserRouter, Route, Routes} from "react-router-dom"
const App = () => {
  return (
    <Router>
      <div className='app'>
        <Navbar/>
        <main style={{minHeight:"80vh"}}>
          <Routes>
            <Route path='/' element={<HomePage/>}/>
            <Route path='/categories' element={<CategoryPage/>}/>
          </Routes>
        </main>
        <Footer/>
      </div>
    </Router>
  )
}

export default App