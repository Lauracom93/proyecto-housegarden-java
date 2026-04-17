import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

/**
 * Configuracion de Vite para el proyecto HouseGarden React.
 * proxy: redirige las peticiones /api al backend Spring Boot
 * en http://localhost:8080/housegarden-spring para evitar
 * problemas de CORS durante el desarrollo.
 *
 * Aprendices: Laura Combita - Diana Amaya - Andres Fontalvo
 * Ficha: 3118327 | SENA 2026
 */
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080/housegarden-spring',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
