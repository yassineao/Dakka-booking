import type { Metadata } from "next";
import "./globals.css";
import Footer from "./components/Footer";

export const metadata: Metadata = {
  title: "Appointments Dashboard",
  description: "Appointments Management Dashboard",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className="antialiased">
        <div className="flex min-h-screen flex-col bg-white">
          
          {/* Main page content */}
          <main className="flex-1 bg-white">
            {children}
          </main>

          {/* Footer */}
          <Footer />
        </div>
       
      </body>
    </html>
  );
}
