.PHONY: all frontend backend  # Declare 'phony' targets (not real files)

all: frontend backend  # The 'all' target depends on frontend and backend

frontend:
	@echo "============================ Started frontend build. ============================"
	@echo "============================ Installing dependencies... ============================"
	cd frontend && npm install
	@echo "============================ Linting frontend... ============================"
	cd frontend && npm run lint
	@echo "============================ Testing frontend... ============================"
	cd frontend && npm run test-headless
	@echo "============================ Building frontend with Node.js and Vite... ============================"
	cd frontend && npm run build
	@echo "============================ Frontend build complete! ============================"


backend:
	@echo "============================ Started backend build. ============================"
	@echo "============================ Removing old frontend files from backend... ============================"
	$(if $(ComSpec), powershell -Command "Remove-Item -Recurse -Force backend/src/main/resources/static/*", rm -rf backend/src/main/resources/static/*)
	@echo "============================ Copying new frontend build to backend... ============================"
	$(if $(ComSpec), powershell -Command "Copy-Item -Recurse -Force frontend/dist/frontend/browser/* backend/src/main/resources/static", cp -r frontend/dist/frontend/browser/* backend/src/main/resources/static)
	@echo "============================ Spotless... ============================"
	cd backend && $(if $(ComSpec), cmd /c "mvn spotless:check", eval "mvn spotless:check")
	@echo "============================ Building backend with Maven... ============================"
	cd backend && mvn clean package
	@echo "============================ Backend build complete! ============================"
