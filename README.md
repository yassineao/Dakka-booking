# Dakka Booking Platform

A comprehensive booking platform built with modern web technologies, featuring a user-friendly frontend for bookings, an admin dashboard for management, and a robust Spring Boot backend API.

## 🚀 Features

- **User Booking System**: Intuitive booking interface with package selection and calendar integration
- **Admin Dashboard**: Comprehensive management interface with analytics, user management, and booking oversight
- **Secure Authentication**: JWT-based authentication system for both users and administrators
- **Database Management**: Automated database migrations with Flyway
- **Modern UI/UX**: Built with Next.js, React, and Tailwind CSS for responsive design
- **Real-time Calendar**: Interactive calendar for booking management and scheduling

## 🛠 Tech Stack

### Backend
- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database access
- **PostgreSQL** - Primary database
- **Flyway** - Database migrations
- **JWT** - Token-based authentication

### Frontend
- **Next.js 16** - React framework
- **React 19** - UI library
- **TypeScript** - Type safety
- **Tailwind CSS** - Styling
- **Material Tailwind** - Component library
- **GSAP** - Animations
- **Lucide React** - Icons

### Dashboard
- **Next.js 16** - React framework
- **React 19** - UI library
- **TypeScript** - Type safety
- **Tailwind CSS** - Styling
- **Neon Database** - Serverless PostgreSQL

### Database
- **Neon** - Serverless PostgreSQL hosting

## 📋 Prerequisites

- **Java 17** or higher
- **Node.js 18** or higher
- **npm** or **yarn**
- **PostgreSQL** database (or Neon account)

## 🔧 Installation

### 1. Clone the Repository
```bash
git clone <repository-url>
cd dakka-booking
```

### 2. Backend Setup
```bash
cd backend

# Set environment variables
export PGHOST_UNPOOLED=<your-neon-host>
export POSTGRES_PASSWORD=<your-db-password>
export JWT_SECRET=<your-jwt-secret>
export PORT=8080

# Run the application
./mvnw spring-boot:run
```

### 3. Frontend Setup
```bash
cd frontend

# Install dependencies
npm install

# Run development server
npm run dev
```

### 4. Dashboard Setup
```bash
cd dashboard

# Install dependencies
npm install

# Run development server
npm run dev
```

## 🚀 Usage

### Starting the Application
1. **Backend**: Runs on `http://localhost:8080`
2. **Frontend**: Runs on `http://localhost:3000`
3. **Dashboard**: Runs on `http://localhost:3001` (configure different port if needed)

### Accessing the Application
- **Public Site**: Visit `http://localhost:3000` to access the booking platform
- **Admin Dashboard**: Visit `http://localhost:3001` and sign in with admin credentials

## 📁 Project Structure

```
dakka-booking/
├── backend/                 # Spring Boot API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/Yassine/dev/api/
│   │   │   │   ├── ApiApplication.java
│   │   │   │   ├── security/          # Security configuration
│   │   │   │   ├── termine/           # Booking entities
│   │   │   │   └── user/              # User management
│   │   │   └── resources/
│   │   │       ├── application.yaml   # Configuration
│   │   │       └── db/migration/      # Flyway migrations
│   └── pom.xml
├── frontend/                # Next.js public site
│   ├── app/
│   │   ├── components/      # React components
│   │   ├── api/             # API routes
│   │   └── page.tsx         # Main page
│   ├── lib/                 # Utilities
│   └── package.json
└── dashboard/               # Next.js admin dashboard
    ├── app/
    │   ├── components/      # Dashboard components
    │   ├── auth/            # Authentication pages
    │   └── dashboard/       # Dashboard pages
    ├── lib/                 # Utilities
    └── package.json
```

## 🔐 Environment Variables

### Backend
- `PGHOST_UNPOOLED`: PostgreSQL host
- `POSTGRES_PASSWORD`: Database password
- `JWT_SECRET`: JWT signing secret
- `PORT`: Server port (default: 8080)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support, email support@dakka-booking.com or create an issue in this repository.

---

Built with ❤️ using Spring Boot, Next.js, and PostgreSQL</content>
<parameter name="filePath">c:\Users\yassu\Dakka-booking\README.md