"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import DashboardLayout from "../components/dashboard/dasboard";
import Sidebar from "../components/Sidebar";

export default function DashboardPage() {
  const [activeComponent, setActiveComponent] = useState("dashboard");
  const router = useRouter();
  const [authorized] = useState(() => {
    if (typeof window === "undefined") return false;
    return Boolean(localStorage.getItem("token"));
  });

  function renderComponent() {
    switch (activeComponent) {
      case "dashboard":
        return <DashboardLayout />;
      // case "overview":
      //   return <Calendar />;
      default:
        return <DashboardLayout />;
    }
  }

  useEffect(() => {
    if (!authorized) {
      router.replace("/auth/signin");
    }
  }, [authorized, router]);

  if (!authorized) {
    return <div>Loading...</div>;
  }

  return (
    <div className="bg-white">
      <div className="flex overflow-hidden bg-white pt-16">
        <Sidebar onSelect={setActiveComponent} />
          {renderComponent()}
      </div>
    </div>
  );
}
