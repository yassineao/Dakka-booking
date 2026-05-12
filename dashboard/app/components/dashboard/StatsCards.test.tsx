import { render, screen } from '@testing-library/react'
import StatsCards from './StatsCards'

describe('StatsCards', () => {
  it('renders all stat cards', () => {
    render(<StatsCards />)

    expect(screen.getByText('Appointments Today')).toBeInTheDocument()
    expect(screen.getByText('Pending Confirmations')).toBeInTheDocument()
    expect(screen.getByText('Cancelled')).toBeInTheDocument()
  })

  it('displays correct stat values', () => {
    render(<StatsCards />)

    expect(screen.getByText('24')).toBeInTheDocument()
    expect(screen.getByText('7')).toBeInTheDocument()
    expect(screen.getByText('3')).toBeInTheDocument()
  })

  it('displays correct change percentages', () => {
    render(<StatsCards />)

    expect(screen.getByText('+12%')).toBeInTheDocument()
    expect(screen.getByText('+4%')).toBeInTheDocument()
    expect(screen.getByText('-2%')).toBeInTheDocument()
  })

  it('applies correct color classes', () => {
    render(<StatsCards />)

    const greenChange = screen.getByText('+12%')
    expect(greenChange).toHaveClass('text-green-500')

    const yellowChange = screen.getByText('+4%')
    expect(yellowChange).toHaveClass('text-yellow-500')

    const redChange = screen.getByText('-2%')
    expect(redChange).toHaveClass('text-red-500')
  })

  it('renders the correct number of stat cards', () => {
    render(<StatsCards />)

    const statCards = screen.getAllByRole('generic', { hidden: true }).filter(
      element => element.className.includes('rounded-lg') && element.className.includes('bg-white')
    )
    expect(statCards).toHaveLength(3)
  })
})