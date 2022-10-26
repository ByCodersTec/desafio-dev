from fastapi import FastAPI, Request, File, UploadFile
from fastapi.responses import HTMLResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates

import shutil

app = FastAPI()

app.mount("/static", StaticFiles(directory="static"), name="static")


templates = Jinja2Templates(directory="templates")


@app.get("/", response_class=HTMLResponse)
async def home(request: Request):
    return templates.TemplateResponse("home.html", {"request": request})


@app.post("/upload/", response_class=HTMLResponse)
async def upload(request: Request, file: UploadFile = File(...)):

    try:
        with open(f"./data/{file.filename}", "wb") as buffred:
            shutil.copyfileobj(file.file, buffred)

        return templates.TemplateResponse("home.html", {"request": request})
    
    except Exception as err:

        return templates.TemplateResponse("home.html", {"request": request})