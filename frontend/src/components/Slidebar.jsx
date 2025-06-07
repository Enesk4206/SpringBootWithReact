import React, { useEffect, useRef } from 'react'

const Sliderbar = ({ children }) => {
  const sliderRef = useRef(null)

  useEffect(() => {
    const slider = sliderRef.current
    if (!slider) return

    const interval = setInterval(() => {
      if (slider.scrollLeft + slider.clientWidth >= slider.scrollWidth) {
        slider.scrollLeft = 0
      } else {
        slider.scrollLeft += 1
      }
    }, 20)

    return () => clearInterval(interval)
  }, [])

  return (
    <div
      ref={sliderRef}
      className="overflow-x-auto whitespace-nowrap scrollbar-hide scroll-smooth"
    >
      {React.Children.map(children, (child, i) => (
        <div key={i} className="inline-block mr-4">
          {child}
        </div>
      ))}
    </div>
  )
}

export default Sliderbar
