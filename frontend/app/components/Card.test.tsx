import { render, screen } from '@testing-library/react'
import Card from './Card'

describe('Card', () => {
  it('renders the main heading', () => {
    render(<Card />)
    const heading = screen.getByText('Was wir bieten')
    expect(heading).toBeInTheDocument()
  })

  it('renders the subtitle', () => {
    render(<Card />)
    const subtitle = screen.getByText('Dakka Marrakechia Nassim')
    expect(subtitle).toBeInTheDocument()
  })

  it('renders the description text', () => {
    render(<Card />)
    const description = screen.getByText(/Wir bringen die authentische marokkanische Hochzeitsstimmung/)
    expect(description).toBeInTheDocument()
  })

  it('renders all service cards', () => {
    render(<Card />)
    expect(screen.getByText('Brautabholung')).toBeInTheDocument()
    expect(screen.getByText('Saalempfang')).toBeInTheDocument()
    expect(screen.getByText('Hochzeit')).toBeInTheDocument()
  })

  it('renders the logo image', () => {
    render(<Card />)
    const logo = screen.getByAltText('Dakka Marrakechia Nassim')
    expect(logo).toBeInTheDocument()
    expect(logo).toHaveAttribute('src', '/Logo.png')
  })
})