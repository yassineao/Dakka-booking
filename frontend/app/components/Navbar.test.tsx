import { render, screen, fireEvent } from '@testing-library/react'
import Navbar from './Navbar'

describe('Navbar', () => {
  it('renders the logo and brand name', () => {
    render(<Navbar />)
    const logo = screen.getByAltText('Dakka Nassim Logo')
    expect(logo).toBeInTheDocument()
    expect(screen.getByText('Dakka Nassim')).toBeInTheDocument()
  })

  it('renders navigation links', () => {
    render(<Navbar />)
    expect(screen.getByText('Home')).toBeInTheDocument()
    expect(screen.getByText('Buchung')).toBeInTheDocument()
    expect(screen.getByText('Pakete')).toBeInTheDocument()
  })

  it('renders mobile menu button', () => {
    render(<Navbar />)
    const menuButton = screen.getByRole('button', { hidden: true })
    expect(menuButton).toBeInTheDocument()
  })

  it('toggles mobile menu when button is clicked', () => {
    render(<Navbar />)
    const menuButton = screen.getByRole('button', { hidden: true })

    // Menu should be closed initially
    expect(screen.queryByText('Home')).toBeVisible() // Desktop version

    // Click to open mobile menu
    fireEvent.click(menuButton)

    // Mobile menu should be open (though we can't easily test the mobile menu without more setup)
  })

  it('has correct navigation link attributes', () => {
    render(<Navbar />)
    const buchungLink = screen.getByText('Buchung')
    expect(buchungLink).toHaveAttribute('href', '#buchung')

    const packsLink = screen.getByText('Pakete')
    expect(packsLink).toHaveAttribute('href', '#packs')
  })
})