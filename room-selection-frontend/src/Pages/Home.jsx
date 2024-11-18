import React from 'react'
import Hero from '../components/Hero/Hero'
import About from '../components/About/About'
import Navbar from '../components/Navbar/Navbar'
const Home = () => {
  return (
    <div>
      <Navbar/>
      <Hero/>
      <About/>
    </div>
  )
}

export default Home